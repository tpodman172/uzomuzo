import * as React from 'react';
import {useCallback, useEffect, useMemo, useState} from 'react';
import {TaskDTO} from '../../../../api/generated';
import {format} from 'date-fns'
import BoardTemplate from "../templates/BoardTemplate";
import {TaskListWithSearch} from "../organisms/TaskListWithSearch";
import {TasksApi} from "../../../../api";
import {withRouter} from 'react-router-dom';
import TaskCard from "../atoms/TaskCard";
import {TaskList} from "../molecules/TaskList";
import {SelectedDateButtons} from "../molecules/SelectedDateButtons";

const BoardPage = () => {

    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [selectedDate, setSelectedDate] = useState<string>(format(new Date(), 'yyyy-MM-dd'))
    const [checkedList, setCheckedList] = useState(new Set<number>());
    const handleDisplayButtonClick = useCallback((targetDate?: string) => {
        targetDate = targetDate || format(new Date(), 'yyyy-MM-dd');
        setSelectedDate(targetDate);
    }, [setSelectedDate]);

    const fetchTasks = async () => {
        // setSelectedDate(targetDate);
        const taskDTOs = await getTaskList();
        setTaskList(taskDTOs);
        const achievementDTOs = await fetchAchievements();

        const completedTaskIds = achievementDTOs
            .filter(value => value.targetDate === selectedDate)
            .filter(value => value.completed)
            .reduce((prev, current) => {
                prev.add(current.taskId);
                return prev;
            }, new Set<number>());

        setCheckedList(completedTaskIds);
    };

    useEffect(() => {
        fetchTasks().then(() => {
        })
    }, [selectedDate])

    const handleCheck = useCallback((id: number, checked: boolean) => {
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

    const taskCards = useMemo(() => {
        let taskCards = taskList.map(task =>
            <TaskCard
                key={task.id}
                taskId={task.id}
                taskTitle={task.title}
                checked={checkedList.has(task.id)}
                onCheck={handleCheck}/>
        );
        return <TaskList taskCards={taskCards}/>;
    }, [taskList, checkedList, handleCheck]);

    const buttons = useMemo(() => {
        const element = <SelectedDateButtons
            onClick={handleDisplayButtonClick}
            selectedDate={selectedDate}/>
        return element;
    }, [handleDisplayButtonClick, selectedDate]);

    const taskListWithSearch = useMemo(() => <TaskListWithSearch
        searchButtons={buttons}
        taskList={taskCards}
    />, [taskCards, buttons]);

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

const fetchAchievements = async () => {
    // const response = await TasksApi().getAchievement(targetDate);
    const response = await TasksApi().getAllAchievement();
    return response.data;
}

const updateProgress = async (taskId: number, isCompleted: boolean, targetDate: string) => {
    TasksApi().putAchievement(taskId, targetDate, isCompleted);
}

export default withRouter(BoardPage);
