import * as React from "react";
import {ChangeEvent, useState} from "react";
import styled from "styled-components";
import {withRouter} from "react-router-dom";
import * as H from 'history'
import {LoginApi} from "../../../api/index";

interface Props {
    history: H.History
}

const LoginPage = (props: Props) => {
    const [userName, setUserName] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const handleIdChange = (e: ChangeEvent<HTMLInputElement>) => {
        setUserName(e.currentTarget.value);
    }
    const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>) => {
        setPassword(e.currentTarget.value);
    }
    const handleLogin = async () => {
        // todo save token to local storage
        const response = await LoginApi().postLogin(userName, password);
        localStorage.setItem('authorization', response.headers.authorization)
        props.history.push('/');
    }
    return <StyledDiv>
        <InputArea>
            <label> ID</label>
            <input type="text" onChange={handleIdChange}/>
        </InputArea>
        <InputArea>
            <label>Pass</label>
            <input type="text" onChange={handlePasswordChange}/>
        </InputArea>
        <button onClick={() => handleLogin()}>ログイン</button>
    </StyledDiv>;
}


export default withRouter(LoginPage);

const InputArea = styled.div`
    margin-top : 12px;
    margin-bottom : 12px;
    display: flex;
    label {
        width: 50px; 
    }
`

const StyledDiv = styled.div`
    margin : 12px:
`