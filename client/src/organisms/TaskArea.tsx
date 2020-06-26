import * as React from 'react';
import {TaskDTO} from "../../api/generated";
import styled from "styled-components";
import TaskCard from "../molecules/TaskCard";

interface Props {
    taskList: TaskDTO[];
    checkedList: Set<number>;
    handleCheck: (checked: boolean, taskId: number) => void;
}

const TaskArea = ({taskList, checkedList, handleCheck}: Props) => {
    return <> {taskList.length != 0 && <TaskUl>{listTask(taskList, checkedList, handleCheck)}</TaskUl>} </>
}

const listTask = (taskList: TaskDTO[], checkedList: Set<number>, handleCheck: (checked: boolean, taskId: number) => void) => {
    return taskList.map(task => {
        return <TaskCard
            key={task.id}
            taskTitle={task.title}
            checked={checkedList.has(task.id)}
            onCheck={(checked => handleCheck(checked, task.id))}/>
    });
}

const TaskUl = styled.ul`
    display:flex;
    flex-wrap:wrap;
    justify-content: space-between;
`;
export default TaskArea;