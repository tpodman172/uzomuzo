import * as React from "react";
import {useState} from "react";
import styled from "styled-components";
import {Link, withRouter} from "react-router-dom";
import * as H from 'history'
import {LoginApi} from "../../../../api";
import {InputWithLabel} from "../../../base/component/molecules/InputWithLabel";

interface Props {
    history: H.History
}

const LoginPage = (props: Props) => {
    const [userName, setUserName] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const handleLogin = async () => {
        const response = await LoginApi().postLogin(userName, password);
        localStorage.setItem('authorization', response.headers.authorization)
        props.history.push('/board');
    }
    return <StyledDiv>
        <h1>ログイン</h1>
        <InputWithLabel label={'user name'} setValue={setUserName}/>
        <InputWithLabel label={'password'} setValue={setPassword}/>
        <button onClick={() => handleLogin()}>ログイン</button>
        <div>
            <Link to="/signUp">新規登録</Link>
        </div>
    </StyledDiv>;
}

export default withRouter(LoginPage);

const StyledDiv = styled.div`
    > * {
       margin-bottom: 12px;
    }
`