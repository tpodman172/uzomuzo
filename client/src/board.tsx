import * as React from 'react';
import {ChangeEvent} from 'react';
import {TaskApi, TaskCreateDTO} from "../api/generated/api";

// Propsの型定義
type IProps = {
    name: string;
}

type IState = {
    taskList: Array<Task>;
    newTask: string
}

type Task = {
    id: number,
    title: string,
}

export class Board extends React.Component<IProps, IState> {
    constructor(props: IProps) {
        super(props);
        this.state = {
            taskList: [],
            newTask: ""
        };
    }

    displayButtonClick() {
        console.log('クリックされました');
        getTaskList().then(taskList => {
            if (taskList) {
                taskList.forEach(value => console.log(value.id + ":" + value.title));
                this.setState({taskList: taskList});
            }
        }).catch(err => {
            console.log(err.message);
        });
    }

    textChange(e: ChangeEvent<HTMLInputElement>) {
        this.setState({newTask: e.currentTarget.value})
    }

    registerButtonClick() {
        createTask({title: this.state.newTask});
    }

    listTask(taskList: Array<Task>) {
        return taskList.map(task => <li key={task.id}>{task.title}</li>);
    }

    render() {
        return (
            <div>
                <h2>{this.props.name}</h2>
                <ul>{this.listTask(this.state.taskList)}</ul>
                <button onClick={() => this.displayButtonClick()}>表示</button>
                <div>
                    <input type="text" onChange={e => this.textChange(e)}/>
                    <button onClick={() => this.registerButtonClick()}>登録</button>
                </div>
            </div>
        );
    }
}

async function getTaskList() {
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

async function createTask(taskCreateDTO: TaskCreateDTO) {
    try {
        const response = await new TaskApi().taskPost(taskCreateDTO);
        //const response = await new TaskApi().taskOptions();
        return response.data;
    } catch (error) {
        console.log(error);
        //throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}