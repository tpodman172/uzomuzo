import * as React from 'react';
import {TaskApi} from "../api/generated/api";
import {Simulate} from "react-dom/test-utils";

// Propsの型定義
type IProps = {
    name: string;
}

type IState = {
    taskList: Array<Task>;
}

type Task = {
    id: number,
    title: string,
}

export class Board extends React.Component<IProps, IState> {
    constructor(props: IProps) {
        super(props);
        this.state = {
            taskList: []
        };
    }

    handleClick() {
        console.log('クリックされました');
        main().then(taskList => {
            if (taskList) {
                taskList.forEach(value => console.log(value.id + ":" + value.title));
                this.setState({taskList: taskList});
            }
        }).catch(err => {
            console.log(err.message);
        });
    }

    listTask() {
        return this.state.taskList.map(task => <li key={task.id}>{task.title}</li>);
    }

    render() {
        return (
            <div>
                <h2>{this.props.name}</h2>
                <ul>{this.listTask()}</ul>
                <button onClick={this.handleClick.bind(this)}>表示</button>
            </div>
        );
    }
}

async function main() {
    try {
        var options = {
            //headers: {'X-SPECIAL-TOKEN': 'aaaaaa'}
        }
        //const response = await new PetsApi().listPets(10);
        const response = await new TaskApi().tasksGet();
        return response.data;
    } catch (error) {
        throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}