import * as React from 'react';
import {ChangeEvent, useContext} from 'react';
import styled from "styled-components";
import {Link} from "react-router-dom";
import {useToken} from "../../../base/hooks/useToken";
// import {useToken} from "../../../user/hooks/useToken";

interface Props {
    handleCreateTextChange: (e: ChangeEvent<HTMLInputElement>) => void;
    handleRegister: () => void;
    taskListWithSearch: JSX.Element;
}

const BoardTemplate = ({taskListWithSearch, handleCreateTextChange, handleRegister}: Props) => {
    // const [userName] = useUserName();
    const {jwtTokenClaims} = useToken();
    return <StyledDiv>
        <Link to='/login'>login</Link>
        <h2>{jwtTokenClaims?.tsk2_user_name}さん、おはようございます！！</h2>
        <h2>あさおきたらやることリスト</h2>
        <TasksArea>
            {taskListWithSearch}
        </TasksArea>
        <CreateNewArea>
            <input type="text" onChange={handleCreateTextChange}/>
            <button onClick={handleRegister}>登録</button>
        </CreateNewArea>
    </StyledDiv>;
}
const StyledDiv = styled.div`
    margin-top:12px;
`;
const TasksArea = styled.div`
    margin-top:12px;
`;
const CreateNewArea = styled.div`
    margin-top:12px;
`;

export default BoardTemplate;
