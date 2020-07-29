import * as React from "react";
import styled from "styled-components";

interface Props {
    taskId: number;
    taskTitle: string;
    checked: boolean;
    onCheck: (id: number, checked: boolean) => void;
    continueCount?: number;
}

const TaskCard = ({taskId, checked, onCheck, taskTitle, continueCount}: Props) => {
    return (
        <StyledLi
            checked={checked}
            onClick={() => onCheck(taskId, !checked)}>
            <div>
                {taskTitle}
            </div>
            {continueCount && <div>{continueCount}</div>}
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
