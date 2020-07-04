import * as React from 'react';
import {ChangeEvent, useCallback, useMemo, useState} from 'react';
import {TaskCreateDTO, TaskDTO} from '../../../api/generated';
import {addDays, format} from 'date-fns'
import BoardTemplate from "../templates/BoardTemplate";
import {TaskListWithSearch} from "../organisms/TaskListWithSearch";
import {TasksApi} from "../../../api";
import {withRouter} from 'react-router-dom';

const BoardPage = () => {

    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [newTask, setNewTask] = useState<string>("");
    const [selectedDate, setSelectedDate] = useState<string>(format(new Date(), 'yyyy-MM-dd'))
    const [checkedList, setCheckedList] = useState(new Set<number>());
    const handleShowList = useCallback(async (targetDate: string) => {
        setSelectedDate(targetDate);
        const taskDTOs = await getTaskList();
        setTaskList(taskDTOs);
        const taskChallengeResultDTOs = await fetchTaskChallengeResults(targetDate);

        const completedTaskIds = taskChallengeResultDTOs
            .filter(value => value.targetDate === targetDate)
            .filter(value => value.completed)
            .reduce((prev, current) => {
                prev.add(current.taskId);
                return prev;
            }, new Set<number>());

        setCheckedList(completedTaskIds);
    }, [setSelectedDate, setTaskList, fetchTaskChallengeResults]);

    const textChange = (e: ChangeEvent<HTMLInputElement>) => {
        setNewTask(e.currentTarget.value);
    }

    const handleRegister = () => {
        createTask({title: newTask}).then(() => {
            // todo show toast?
        });
    }

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

    const deleteTask = async (id: number) => {
        try {
            await TasksApi.taskDelete(id);
        } catch (e) {
            console.log(e);
        }
    };

    const taskListWithSearch = useMemo(() => <TaskListWithSearch
        searchButtons={[<button key={1} onClick={() => handleShowList(format(new Date(), 'yyyy-MM-dd'))}>今日</button>,
            <button key={2} onClick={() => handleShowList(format(addDays(new Date(), -1), 'yyyy-MM-dd'))}>昨日</button>]}
        checkedList={checkedList}
        handleCheck={handleCheck}
        taskList={taskList}
    />, [handleShowList, checkedList, handleCheck, taskList]);

    console.log('render: board');
    return (
        <BoardTemplate
            handleCreateTextChange={textChange}
            handleRegister={handleRegister}
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
        const response = await TasksApi.getTasks();
        // todo implement login
        localStorage.setItem('authorization', response.headers['authorization']);

        return response.data;
    } catch (error) {
        throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

const fetchTaskChallengeResults = async (targetDate: string) => {
    const response = await TasksApi.getAchievement(targetDate);
    return response.data;
}

// todo move...
async function createTask(taskCreateDTO: TaskCreateDTO) {
    try {
        const response = await TasksApi.postTask(taskCreateDTO);
        //const response = await TasksApi.taskOptions();
        return response.data;
    } catch (error) {
        console.log(error);
        //throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

const updateProgress = async (taskId: number, isCompleted: boolean, targetDate: string) => {
    TasksApi.putAchievement(taskId, targetDate, isCompleted);
}

export default withRouter(BoardPage);
