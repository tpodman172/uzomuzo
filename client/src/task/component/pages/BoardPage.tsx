import * as React from 'react';
import {useCallback, useEffect, useMemo, useState} from 'react';
import {TaskDTO} from '../../../../api/generated';
import {addDays, compareAsc, format, isToday, isYesterday, parse} from 'date-fns'
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
    const [taskAchievementMap, setTaskAchievementMap] = useState<{ [taskId: number]: Date[] }>([]);
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

        // const map = achievementDTOs.reduce((prev, current) => {
        //     (prev.get(current.taskId) || (prev.set(current.taskId, []).get(current.taskId)) as Date[]).push(parse(current.targetDate, 'yyyy-MM-dd', new Date()));
        //     return prev;
        // }, new Map<number, Date[]>())

        const map1 = achievementDTOs
            .filter(value => value.completed)
            .reduce((prev, current) => {
                (prev[current.taskId] || (prev[current.taskId] = [])).push(parse(current.targetDate, 'yyyy-MM-dd', new Date()));
                return prev;
            }, {} as { [taskId: number]: Date[] });

        setTaskAchievementMap(map1);

        // let numbers = Object.entries(map1).reduce((prev, [taskId, dates]) => {
        //     prev[Number.parseInt(taskId)] = calcContinueCount(dates);
        //     return prev;
        // }, {} as { [taskId: number]: number });

        // map.forEach((targetDates, taskId) => {
        //     const latestDate = targetDates[targetDates.length - 1];
        //     if (isToday(latestDate) || isYesterday(latestDate)) {
        //
        //     }
        //
        // })

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
            setTaskAchievementMap((prevState => {
                const achievementDates = (prevState[id] || (prevState[id] = []));
                achievementDates.push(parse(selectedDate, 'yyyy-MM-dd', new Date()));
                achievementDates.sort(compareAsc);
                return Object.assign({}, prevState);
            }))
        } else {
            setCheckedList((prev) => {
                prev.delete(id);
                return new Set(prev);
            });
            setTaskAchievementMap((prevState => {
                const achievementDates = prevState[id];
                prevState[id] = achievementDates.filter(date => format(date, 'yyyy-MM-dd') !== selectedDate);
                return Object.assign({}, prevState);
            }))
        }
    }, [selectedDate, setCheckedList]);

    const taskCards = useMemo(() => {
        let taskCards = taskList.map(task =>
            <TaskCard
                key={task.id}
                taskId={task.id}
                taskTitle={task.title}
                checked={checkedList.has(task.id)}
                onCheck={handleCheck}
                continueCount={calcContinueCount(taskAchievementMap[task.id]) || undefined}
            />
        );
        return <TaskList taskCards={taskCards}/>;
    }, [taskList, checkedList, handleCheck, taskAchievementMap]); // ここにcontinueCountMapを入れる

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
    const response = await TasksApi().getAchievements();
    return response.data;
}

const updateProgress = async (taskId: number, isCompleted: boolean, targetDate: string) => {
    TasksApi().putAchievement(taskId, targetDate, isCompleted);
}

const calcContinueCount = (dates?: Date[]) => {
    if (!dates) return 0;
    const latestDate = dates[dates.length - 1];
    if (!isToday(latestDate) && !isYesterday(latestDate)) {
        return 0;
    } else {
        let counter = 1;
        for (let i = dates.length - 1; i > 0; i--) {
            if (dates[i].getDate() === addDays(dates[i - 1], 1).getDate()) {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }
}

export default withRouter(BoardPage);
