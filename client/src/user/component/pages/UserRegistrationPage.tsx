import * as React from "react";
import {useState} from "react";
import {Link, withRouter} from "react-router-dom";
import {InputWithLabel} from "../../../base/component/molecules/InputWithLabel";
import {UserApi} from "../../../../api";
import styled from "styled-components";
import * as H from 'history'

interface Props {
    history: H.History
}

const UserRegistrationPage = (props: Props) => {
    const [userName, setUserName] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const handleSignUp = () => {
        console.log('aaaaaaaaaa');
        UserApi().putUser({userName, password}).then((response) => {
            localStorage.setItem('authorization', response.headers.authorization)
            props.history.push('/');
        }).catch((e) => {
            console.log(e.response); // todo error handling
        });
    }
    return <StyledDiv>
        <h1>新規登録</h1>
        <InputWithLabel label={'user name'} setValue={setUserName}/>
        <InputWithLabel label={'password'} setValue={setPassword}/>
        <button onClick={() => handleSignUp()}>新規登録</button>
        <div>
            <Link to="/login">ログイン</Link>
        </div>
    </StyledDiv>;
}

export default withRouter(UserRegistrationPage);

const StyledDiv = styled.div`
  > * {
      margin-bottom: 12px;
  }
`