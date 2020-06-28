import * as React from 'react';
import {ChangeEvent, useMemo, useState} from 'react';
import Children1FC from "./ChildrenFC1";
import Children2FC from "./ChildrenFC2";


const ParentFC = () => {
    const [x, setX] = useState<string>('');
    const [y, setY] = useState<string>('');
    const useMemo1 = useMemo(() => <Children2FC y={y}/>,[y]);
    console.log('render: parent')
    return (
        <>
            <input type='text' onChange={(e: ChangeEvent<HTMLInputElement>) => {
                setX(e.currentTarget.value);
            }} />
            <Children1FC x={x}/>
            {useMemo1}
        </>);
}
export default ParentFC;
