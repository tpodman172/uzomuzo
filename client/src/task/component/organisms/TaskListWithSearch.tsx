import * as React from 'react';
import {SearchButtons} from "../molecules/SearchButtons";
import styled from "styled-components";

interface Props {
    searchButtons: JSX.Element[];
    taskList: JSX.Element;
}

export const TaskListWithSearch = ({searchButtons, taskList}: Props) => {
    console.log('render taskListWithSearch');
    return <>
        <SearchButtons buttons={searchButtons}/>
        <StyledDiv>
            {taskList}
        </StyledDiv>
    </>;
}

const StyledDiv = styled.div`
    margin-top:12px; 
`
