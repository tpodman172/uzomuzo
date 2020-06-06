import * as React from 'React';

interface Props {
   x: string
}

const Children1FC = ({x}: Props) => {
   console.log('render: children1')
   return <>{x}</>
}

export default Children1FC;