import * as React from "react";
import styled from "styled-components";

interface Props {
    taskTitle: string;
    checked: boolean;
    onCheck: (checked: boolean) => void;
}

const TaskCard = ({checked, onCheck, taskTitle}: Props) => {
    return (
        <StyledLi
            checked={checked}
            onClick={() => onCheck(!checked)}>
            {taskTitle}
        </StyledLi>
    );
}
const StyledLi = styled.li<{ checked: boolean }>`
    background: ${(props) => props.checked ? '#ff9800' : 'white'};
    list-style: none;
    height : 100px;
    padding : 8px;
    filter: drop-shadow(3px 3px 3px rgba(0,0,0,0.6));
    box-sizing: border-box;
    width: calc(50% - 6px);
`;

export default TaskCard;
