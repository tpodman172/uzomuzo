import * as React from 'react';
import {useState} from "react";

interface Props {
    checked: boolean
    onCheck: (checked: boolean) => void;
}

const CheckBox = ({checked, onCheck}: Props) => {
    const [localChecked, setLocalChecked] = useState<boolean>(checked);
    const handleChecked = () => {
        setLocalChecked(!localChecked);
        onCheck(!localChecked);
    }
    return (
        <>
            <input onChange={() => handleChecked()} checked={localChecked} type="checkbox"/>
        </>
    )
}

export default CheckBox;