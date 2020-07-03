import * as React from "react";
import styled from "styled-components";

interface Props {
    buttons: JSX.Element[];
}
export const SearchButtons = ({buttons}:Props) => {
    return <StyledDiv>
        {buttons}
    </StyledDiv>;
}


const StyledDiv = styled.div`
button:not(:first-child){
margin-left:12px;
}
`