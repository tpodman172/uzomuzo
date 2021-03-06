import * as React from "react";
import {ChangeEvent, useState} from "react";
import styled from "styled-components";
import {Buttons} from "../../../task/component/molecules/Buttons";

interface Props {
    taskId: number;
    taskTitle: string;
    onUpdate: (taskId: number, title: string) => void;
    onDelete: (taskId: number) => void;
    onCancel: () => void;
}

const EditableTaskCard = ({taskId, onUpdate, onDelete, onCancel, taskTitle}: Props) => {
    const [updateTitle, setUpdateTitle] = useState<string>(taskTitle);

    const handleTextChange = (e: ChangeEvent<HTMLInputElement>) => {
        setUpdateTitle(e.currentTarget.value);
    }

    return (
        <StyledLi>
            <StyledText value={updateTitle} onChange={handleTextChange}/>
            <ButtonArea>
                <Buttons buttons={[<button key={1} onClick={() => onUpdate(taskId, updateTitle)}>update</button>,
                    <button key={2} onClick={() => onDelete(taskId)}>delete</button>,
                    <button key={3} onClick={onCancel}>cancel</button>
                ]}/>
            </ButtonArea>
        </StyledLi>
    );
}

const StyledLi = styled.li`
    background: white;
    list-style: none;
    height : 100px;
    padding : 8px;
    filter: drop-shadow(3px 3px 3px rgba(0,0,0,1));
    box-sizing: border-box;
    width: calc(50% - 6px);
`;

const ButtonArea = styled.div`
    display: flex;
    justify-content: flex-end;
    margin-top: 12px;
`

const StyledText = styled.input`
    height: 28px;
    width: 100%;
    box-sizing: border-box;
`

export default EditableTaskCard;
