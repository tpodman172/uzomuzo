import * as React from 'React';
import styled from "styled-components";
import {addDays, format} from "date-fns"
import {TaskDTO} from "../../api/generated";
import {ChangeEvent} from "react";
import TaskArea from "../organisms/TaskArea";

interface Props {
    handleShowList: (targetDate: string) => void; // todo:今日と昨日のメソッドをそれぞれ上位から渡す
    targetDate: string;
    taskList: TaskDTO[];
    handleCreateTextChange: (e: ChangeEvent<HTMLInputElement>) => void;
    handleRegister: () => void;
    checkedList: Set<number>;
    handleCheck: (checked:boolean, taskId:number) => void;
}

const BoardTemplate = ({handleShowList, taskList, handleCreateTextChange, handleRegister, checkedList, handleCheck}: Props) => {
    return <StyledDiv>
        <h2>あさおきたらやることリスト</h2>
        <ButtonArea>
            <button onClick={() => handleShowList(format(new Date(), 'yyyy-MM-dd'))}>今日</button>
            <button onClick={() => handleShowList(format(addDays(new Date(), -1), 'yyyy-MM-dd'))}>昨日</button>
        </ButtonArea>
        <TaskArea
            checkedList={checkedList}
            handleCheck={handleCheck}
            taskList={taskList}
        />
        <CreateNewArea>
            <input type="text" onChange={handleCreateTextChange}/>
            <button onClick={handleRegister}>登録</button>
        </CreateNewArea>
    </StyledDiv>;
}
const StyledDiv = styled.div`
    margin-top:12px;
`;
const ButtonArea = styled.div`
    margin-top:12px;
    button:not(:first-child){
        margin-left:12px
    }
`;
const CreateNewArea = styled.div`
    margin-top:12px;
`;
const TaskUl = styled.ul`
    display:flex;
    flex-wrap:wrap;
    justify-content: space-between;
`;

export default BoardTemplate;
