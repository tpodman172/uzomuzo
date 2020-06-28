import * as React from 'react';
import * as ReactDOM from 'react-dom';
import BoardPage from "./Pages/BoardPage";
import reset from 'styled-reset'
import styled, {createGlobalStyle} from "styled-components";

class App extends React.Component {
    render() {
        return (
            <StyledDiv>
                <GlobalStyle/>
                <h2>こうへい君、おはようございます！！</h2>
                <BoardPage />
            </StyledDiv>
        );
    }
}

const GlobalStyle = createGlobalStyle`
  ${reset}
  h2 {
    font-size:18px;
  }
  /* other styles */
`
const StyledDiv = styled.div`
margin : 12px
`
ReactDOM.render(<App/>, document.querySelector('#app'));