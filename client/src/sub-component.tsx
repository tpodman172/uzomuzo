import * as React from 'react';
import {PetsApi} from "../api/generated/api";

// Propsの型定義
interface IProps {
    name: string;
}

interface IState {
    count: number;
}

export class SubComponent extends React.Component<IProps, IState> {
    constructor(props: any) {
        super(props);
        this.state = {
            count: 0,
        };
    }

    handleClick() {
        console.log('クリックされました');
        main();
        this.setState({
            count: this.state.count + 1,
        });
    }

    render() {

        return (
            <div>
                <h2>{this.props.name}</h2>
                <div>{this.state.count}</div>
                <button onClick={this.handleClick.bind(this)}>Add +1</button>
            </div>
        );
    }
}

const axios = require('axios');
//const url = "https://qiita.com/api/v2/items";
const url = "http://localhost:8080/v1/pets";

async function main() {
    try {
        var options = {

            //headers: {'X-SPECIAL-TOKEN': 'aaaaaa'}
        }
        const response = await new PetsApi().listPets(10);
        response.data.forEach(value => console.log(value.id + ":" + value.name))
        // const res = await axios.get(url);
        // const items = res.data;
        // for (const item of items) {
        //     console.log(`${item.id}: \t${item.name}`);
        // }
    } catch (error) {
        console.log(`Error! HTTP Status: ${error.response}`);
    }
}