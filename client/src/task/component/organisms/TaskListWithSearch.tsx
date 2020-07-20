import * as React from 'react';
import styled from "styled-components";

interface Props {
    searchButtons: JSX.Element;
    taskList: JSX.Element;
}

export const TaskListWithSearch = ({searchButtons, taskList}: Props) => {
    console.log('render taskListWithSearch');
    return <>
        {searchButtons}
        <StyledDiv>
            {taskList}
        </StyledDiv>
    </>;
}

const StyledDiv = styled.div`
    margin-top:12px; 
`
