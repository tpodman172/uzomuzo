import * as React from 'React';
import styled from "styled-components";
import {addDays, format} from "date-fns"
import {TaskDTO} from "../../api/generated";
import {ChangeEvent} from "react";

interface Props {
    handleShowList: (targetDate: string) => void; // todo:今日と昨日のメソッドをそれぞれ上位から渡す
    targetDate: string;
    taskList: TaskDTO[];
    handleListTask: () => JSX.Element[];
    handleCreateTextChange: (e: ChangeEvent<HTMLInputElement>) => void;
    handleRegister: () => void;

}

const BoardTemplate = ({handleShowList, taskList, handleListTask, handleCreateTextChange, handleRegister}: Props) => {
    return <StyledDiv>
        <h2>あさおきたらやることリスト</h2>
        <ButtonArea>
            <button onClick={() => handleShowList(format(new Date(), 'yyyy-MM-dd'))}>今日</button>
            <button onClick={() => handleShowList(format(addDays(new Date(), -1), 'yyyy-MM-dd'))}>昨日</button>
        </ButtonArea>
        <TaskArea>
            {taskList.length != 0 && <TaskUl>{handleListTask()}</TaskUl>}
        </TaskArea>
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
const TaskArea = styled.div`
`
const TaskUl = styled.ul`
    display:flex;
    flex-wrap:wrap;
    justify-content: space-between;
`;

export default BoardTemplate;
