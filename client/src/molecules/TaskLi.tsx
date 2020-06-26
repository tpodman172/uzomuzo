import * as React from "react";
import styled from "styled-components";

interface Props {
    taskTitle: string;
    checked: boolean;
    onCheck: (checked: boolean) => void;
}

const TaskLi = ({checked, onCheck, taskTitle}: Props) => {
    return (
        <StyledLi
            checked={checked}
            onClick={() => onCheck(!checked)}>
            {taskTitle}
        </StyledLi>
    );
}
const StyledLi = styled.li<{ checked: boolean }>`
    background: ${(props) => props.checked ? 'palevioletred' : 'white'};
    list-style: none;
    height : 100px;
    width: 46%;
    margin-top : 12px;
    border: solid;
`;

export default TaskLi;
