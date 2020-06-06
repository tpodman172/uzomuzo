import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import Children1FC from "./ChildrenFC1";
import Children2FC from "./ChildrenFC2";


const ParentFC = () => {
    const [x, setX] = useState<string>('');
    const [y, setY] = useState<string>('');
    console.log('render: parent')
    return (
        <>
            <input type='text' onChange={(e: ChangeEvent<HTMLInputElement>) => {
                setX(e.currentTarget.value);
            }} />
            <Children1FC x={x}/>
            <Children2FC y={y}/>
        </>);
}
export default ParentFC;
