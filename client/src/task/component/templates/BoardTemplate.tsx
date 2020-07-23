import * as React from 'react';
import styled from "styled-components";
import {Link} from "react-router-dom";
import {useToken} from "../../../base/hooks/useToken";

// import {useToken} from "../../../user/hooks/useToken";

interface Props {
    taskListWithSearch: JSX.Element;
}

const BoardTemplate = ({taskListWithSearch}: Props) => {
    // const [userName] = useUserName();
    const {jwtTokenClaims} = useToken();
    return <StyledDiv>
        <h2>{jwtTokenClaims?.tsk2_user_name} のやることリスト</h2>
        {taskListWithSearch}
        <LinkArea>
            <Link to='/boardAdmin'>やることの管理</Link>
            <Link to='/profile'>プロフィール</Link>
        </LinkArea>
    </StyledDiv>;
}
const StyledDiv = styled.div`
    > * {
        margin-top:12px;
    }
`;

const LinkArea = styled.div`
    > * {
        display:block;
        margin-bottom:12px;
    }
`;
export default BoardTemplate;
