import * as React from 'react';
import * as ReactDOM from 'react-dom';
import Board from "./board";
import reset from 'styled-reset'
import styled, {createGlobalStyle} from "styled-components";

class App extends React.Component {
    render() {
        return (
            <StyledDiv>
                <GlobalStyle/>
                <h2>こうへい君、おはようございます！！</h2>
                <Board name="あさおきたらやることリスト"/>
            </StyledDiv>
        );
    }
}

const GlobalStyle = createGlobalStyle`
  ${reset}
  h2 {
    font-size:20px;
  }
  /* other styles */
`
const StyledDiv = styled.div`
margin : 10px
`
ReactDOM.render(<App/>, document.querySelector('#app'));