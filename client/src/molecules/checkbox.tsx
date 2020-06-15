import * as React from 'react';
import {useState} from "react";

interface Props {
    checked: boolean
    onCheck: (checked: boolean) => void;
}

const CheckBox = ({checked, onCheck}: Props) => {
    // todo 内部にstateを持つことは正しいのだろうか
    // 親にチェックボックスのstateを持つこともできるが親側でcheckedの制御もしてあげないといけない（いずれにせよ初期化でやってるか…）
    return (
        <>
            <input onChange={() => onCheck(!checked)} checked={checked} type="checkbox"/>
        </>
    )
}

export default CheckBox;