import * as React from 'react';
import {TaskDTO} from "../../api/generated";
import {SearchButtons} from "../molecules/SearchButtons";
import {TaskList} from "../molecules/TaskList";
import styled from "styled-components";

interface Props {
    searchButtons: JSX.Element[];
    taskList: TaskDTO[];
    checkedList: Set<number>;
    handleCheck: (checked: boolean, taskId: number) => void;
}

export const TaskListWithSearch = ({searchButtons, taskList, checkedList, handleCheck}: Props) => {
    console.log('render taskListWithSearch');
    return <>
        <SearchButtons buttons={searchButtons}/>
        <StyledDiv>
            <TaskList taskList={taskList} checkedList={checkedList} handleCheck={handleCheck}/>
        </StyledDiv>
    </>;
}

const StyledDiv = styled.div`
    margin-top:12px; 
`
