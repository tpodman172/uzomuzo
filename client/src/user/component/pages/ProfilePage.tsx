import * as React from 'react'
import {useEffect, useState} from 'react'
import {useToken} from "../../../base/hooks/useToken";
import {InputWithLabel} from "../../../base/component/molecules/InputWithLabel";
import {UserApi} from "../../../../api";
import * as H from 'history'
import styled from "styled-components";
import {Link} from "react-router-dom";
import {ErrorMessage} from "../../../base/component/molecules/ErrorMessage";

interface Props {
    history: H.History
}

export const ProfilePage = (props: Props) => {
    const {jwtTokenClaims, setToken} = useToken();
    const [userName, setUserName] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string | undefined>(undefined);
    useEffect(() => {
        setUserName(jwtTokenClaims?.tsk2_user_name || '');
    }, [jwtTokenClaims]);
    const handleClick = () => {
        UserApi().putUser({userName: userName}).then(response => {
            setToken(response.headers.authorization);
            props.history.push('/board');
        }).catch((e) => {
            switch (e.response?.status) {
                case 400:
                    setErrorMessage('1もじいじょう いれてね');
                    break;
                case 409:
                    setErrorMessage('そのなまえは ほかのおともだちが つかってるよ');
                    break;
                default:
                    setErrorMessage('なんかぐあいがわるいみたい😪');
                    break;
            }
        })
    }
    return <StyledDiv>
        {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
        <InputWithLabel label={'なまえ'} setValue={setUserName} value={userName}/>
        <button onClick={handleClick}>更新</button>
        <div>
            <Link to='/board'>タスク一覧</Link>
        </div>
    </StyledDiv>;
}

const StyledDiv = styled.div`
    > * {
        margin-bottom:12px;
    }
`
