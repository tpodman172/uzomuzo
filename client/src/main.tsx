import * as React from 'react';
import * as ReactDOM from 'react-dom';
import Board from "./board";

class App extends React.Component {
    render() {
        return (
            <div>
                <h2>こうへい君、おはようございます！！</h2>
                <Board name="あさおきたらやるとリスト"/>
            </div>
        );
    }
}

ReactDOM.render(<App/>, document.querySelector('#app'));