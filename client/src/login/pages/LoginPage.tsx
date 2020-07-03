import * as React from "react";
import styled from "styled-components";
import {withRouter} from "react-router-dom";
import * as H from 'history'

interface Props {
    history: H.History
}

const LoginPage = (props: Props) => {
    return <StyledDiv>
        <InputArea>
            <label> ID</label>
            <input type="text"/>
        </InputArea>
        <InputArea>
            <label>Pass</label>
            <input type="text"/>
        </InputArea>
        <button onClick={() => props.history.push('/')}>ログイン</button>
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