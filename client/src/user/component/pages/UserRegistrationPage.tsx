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
    const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);
    const handleSignUp = () => {
        UserApi().putUser({userName, password}).then((response) => {
            setErrorMessage(undefined);
            localStorage.setItem('authorization', response.headers.authorization)
            props.history.push('/board');
        }).catch((e) => {
            switch (e.response?.status){
                case 400:
                    setErrorMessage('それぞれ 1もじいじょう いれてね');
                    break;
                case 409:
                    setErrorMessage('そのなまえは ほかのおともだちが つかってるよ');
                    break;
                default:
                    setErrorMessage('なんかぐあいがわるいみたい😪');
                    break;
            }
        });
    }
    return <StyledDiv>
        <h1>新規登録</h1>
        {errorMessage && <div>{errorMessage}</div>}
        <InputWithLabel label={'なまえ'} setValue={setUserName}/>
        <InputWithLabel label={'あいことば'} setValue={setPassword}/>
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