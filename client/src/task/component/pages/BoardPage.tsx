import * as React from 'react';
import {useCallback, useEffect, useMemo, useState} from 'react';
import {TaskDTO} from '../../../../api/generated';
import {addDays, format} from 'date-fns'
import BoardTemplate from "../templates/BoardTemplate";
import {TaskListWithSearch} from "../organisms/TaskListWithSearch";
import {TasksApi} from "../../../../api";
import {withRouter} from 'react-router-dom';
import TaskCard from "../atoms/TaskCard";
import {TaskList} from "../molecules/TaskList";
import {Buttons} from "../molecules/Buttons";

const BoardPage = () => {

    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [selectedDate, setSelectedDate] = useState<string>(format(new Date(), 'yyyy-MM-dd'))
    const [checkedList, setCheckedList] = useState(new Set<number>());
    const handleShowList = useCallback(async (targetDate?: string) => {
        targetDate = targetDate || format(new Date(), 'yyyy-MM-dd');
        setSelectedDate(targetDate);
        const taskDTOs = await getTaskList();
        setTaskList(taskDTOs);
        const achievementDTOs = await fetchAchievements(targetDate);

        const completedTaskIds = achievementDTOs
            .filter(value => value.targetDate === targetDate)
            .filter(value => value.completed)
            .reduce((prev, current) => {
                prev.add(current.taskId);
                return prev;
            }, new Set<number>());

        setCheckedList(completedTaskIds);
    }, [setSelectedDate, setTaskList, fetchAchievements]);

    useEffect(() => {
        handleShowList().then(() => {
        })
    }, [])

    const handleCheck = useCallback((checked: boolean, id: number) => {
        console.log(selectedDate);
        updateProgress(id, checked, selectedDate).catch((e) => {
            console.log(e);
            // todo checkを元に戻す...？
        });
        if (checked) {
            setCheckedList((prev) => new Set(prev.add(id)));
        } else {
            setCheckedList((prev) => {
                prev.delete(id);
                return new Set(prev);
            });
        }
    }, [selectedDate, setCheckedList]);

    const makeTaskCards = (taskList: TaskDTO[], checkedList: Set<number>, handleCheck: (checked: boolean, taskId: number) => void) => {
        let taskCards = taskList.map(task =>
            <TaskCard
                key={task.id}
                taskTitle={task.title}
                checked={checkedList.has(task.id)}
                // todo 即時関数を渡している...
                onCheck={(checked => handleCheck(checked, task.id))}/>
        );
        return <TaskList taskCards={taskCards}/>;
    }

    const makeButtons = useCallback(() => {
        let buttons = [
            <button key={1} onClick={() => handleShowList()}>今日</button>,
            <button key={2} onClick={() => handleShowList(format(addDays(new Date(), -1), 'yyyy-MM-dd'))}>昨日</button>
        ];
        return <Buttons buttons={buttons}/>;
    }, [handleShowList]);

    const taskListWithSearch = useMemo(() => <TaskListWithSearch
        searchButtons={makeButtons()}
        taskList={makeTaskCards(taskList, checkedList, handleCheck)}
    />, [checkedList, handleCheck, taskList, makeButtons]);

    console.log('render: board');
    return (
        <BoardTemplate
            taskListWithSearch={taskListWithSearch}
        />
    );
}

async function getTaskList() {
    try {
        var options = {
            //headers: {'X-SPECIAL-TOKEN': 'aaaaaa'}
        }
        //const response = await new PetsApi().listPets(10);
        const response = await TasksApi().getTasks();

        return response.data;
    } catch (error) {
        throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

const fetchAchievements = async (targetDate: string) => {
    const response = await TasksApi().getAchievement(targetDate);
    return response.data;
}

const updateProgress = async (taskId: number, isCompleted: boolean, targetDate: string) => {
    TasksApi().putAchievement(taskId, targetDate, isCompleted);
}

export default withRouter(BoardPage);
