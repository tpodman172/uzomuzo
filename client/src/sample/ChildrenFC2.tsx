import * as React from 'React';

interface Props {
   y: string
}

const Children2FC = ({y}: Props) => {
   console.log('render: children2')
   return <>{y}</>
}

export default Children2FC;