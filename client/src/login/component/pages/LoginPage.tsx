import * as React from "react";
import {useEffect, useState} from "react";
import styled from "styled-components";
import {Link, withRouter} from "react-router-dom";
import * as H from 'history'
import {LoginApi} from "../../../../api";
import {InputWithLabel} from "../../../base/component/molecules/InputWithLabel";
import {useToken} from "../../../base/hooks/useToken";

interface Props {
    history: H.History
}

const LoginPage = (props: Props) => {
    const [userName, setUserName] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);
    const {setToken} = useToken();

    useEffect(() => {
        localStorage.removeItem('authorization');
    },[]);

    const handleLogin = async () => {
        setErrorMessage(undefined);
        LoginApi().postLogin(userName, password)
            .then(response => {
                console.log('success login');
                setToken(response.headers.authorization);
                props.history.push('/board');
            })
            .catch(e => {
                if (e.response?.status === 401) {
                    setErrorMessage('「なまえ」か「あいことば」がまちがっているよ')
                } else {
                    setErrorMessage('なんかぐあいがわるいみたい😪')
                }
            });
    }

    return <StyledDiv>
        <h1>ログイン</h1>
        {errorMessage && <div>{errorMessage}</div>}
        <InputWithLabel label={'なまえ'} setValue={setUserName}/>
        <InputWithLabel label={'あいことば'} setValue={setPassword}/>
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