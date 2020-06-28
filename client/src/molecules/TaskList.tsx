import * as React from "react";
import styled from "styled-components";
import {TaskDTO} from "../../api/generated";
import TaskCard from "../atoms/TaskCard";

interface Props {
    taskList: TaskDTO[];
    checkedList: Set<number>;
    handleCheck: (checked: boolean, taskId: number) => void;
}

export const TaskList = ({checkedList, handleCheck, taskList}: Props) => {
    return <>
        {taskList.length != 0 &&
        <TaskUl>{displayTaskCards(taskList, checkedList, handleCheck)}</TaskUl>}
    </>;
}

const displayTaskCards = (taskList: TaskDTO[], checkedList: Set<number>, handleCheck: (checked: boolean, taskId: number) => void) => {
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
    li:nth-of-type(n + 3){
        margin-top: 12px;
    }
`;
