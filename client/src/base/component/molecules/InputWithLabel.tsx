import * as React from "react";
import {ChangeEvent} from "react";
import styled from "styled-components";

interface Props{
    label: string;
    setValue: (userName: string) => void;
    value?: string;
    placeholder?: string;
}

export const InputWithLabel = ({label, value, setValue, placeholder}: Props) => {
    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setValue(e.currentTarget.value);
    }

    return <InputArea>
        <label> {label}</label>
        <input type='text' onChange={handleChange} value={value} placeholder={placeholder}/>
    </InputArea>
}

const InputArea = styled.div`
    display: flex;
    align-items: center;
    label {
        width: 132px; 
    }
    input {
        width:100%;
    }
`