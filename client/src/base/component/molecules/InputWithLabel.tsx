import * as React from "react";
import styled from "styled-components";
import {ChangeEvent} from "react";

interface Props{
    label: string;
    setValue: (userName:string) => void;
    placeholder?: string
}

export const InputWithLabel = ({label, setValue, placeholder}: Props) => {
    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        setValue(e.currentTarget.value);
    }

    console.log(placeholder);

    return <InputArea>
        <label> {label}</label>
        <input type='text' onChange={handleChange} placeholder={placeholder}/>
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