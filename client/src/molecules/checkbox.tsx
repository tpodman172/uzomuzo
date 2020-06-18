import * as React from 'react';

interface Props {
    checked: boolean
    onCheck: (checked: boolean) => void;
}

const CheckBox = ({checked, onCheck}: Props) => {
    // 内部にstateを持つことは正しいのだろうか
    // パフォーマンスの観点では正しいかも？
    // ただそれも親コンポーネントが子コンポーネントの値を知らなくてもよい場合に限られる
    // 結局、子コンポーネントへの入力値を親に渡さないといけない場合は子に持つ意味がないのでは
    // なぜなら親でそのstateを持つ必要があり、親がrerenderされたら子はrerenderされるから

    return (
        <>
            <input onChange={() => onCheck(!checked)} checked={checked} type="checkbox"/>
        </>
    )
}

export default CheckBox;