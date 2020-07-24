import * as React from "react";
import styled from "styled-components";
import {addDays, format} from "date-fns";

interface Props {
    onClick: (targetDay: string) => void;
    selectedDate: string;
}

// fixme このコンポーネントの設計の責務がいまいち曖昧な感じがする
export const SelectedDateButtons = ({onClick, selectedDate}: Props) => {
    const today = format(new Date(), 'yyyy-MM-dd')
    const yesterday = format(addDays(new Date(), -1), 'yyyy-MM-dd')
    return <StyledDiv>
        {selectedDate !== yesterday && <button onClick={() => onClick(yesterday)}>昨日</button>}
        <div>{selectedDate}</div>
        {selectedDate !== today && <button onClick={() => onClick(today)}>今日</button>}
    </StyledDiv>;
}

const StyledDiv = styled.div`
    display: flex;
    align-items: center;
    *:not(:first-child){
        margin-left:12px;
}
`