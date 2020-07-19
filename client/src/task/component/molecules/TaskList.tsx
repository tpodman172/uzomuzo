import * as React from "react";
import styled from "styled-components";

interface Props {
    taskCards: JSX.Element[];
}

export const TaskList = ({taskCards}: Props) => {
    return <>
        <TaskUl>{taskCards}</TaskUl>
    </>;
}

const TaskUl = styled.ul`
    display:flex;
    flex-wrap:wrap;
    justify-content: space-between;
    li:nth-of-type(n + 3){
        margin-top: 12px;
    }
`;
