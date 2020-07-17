import * as React from "react";
import styled from "styled-components";
import {ChangeEvent} from "react";

interface Props{
    label: string;
    setValue: (userName:string) => void;
}


export const InputWithLabel = ({label, setValue}: Props) => {
    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setValue(e.currentTarget.value);
    }

    return <InputArea>
        <label> {label}</label>
        <input type="text" onChange={handleChange}/>
    </InputArea>
}

const InputArea = styled.div`
    display: flex;
    label {
        width: 100px; 
    }
`