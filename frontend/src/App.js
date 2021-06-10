import React from 'react';
import './App.css';
import FormSearch from './components/FormSearch';
import SampleMap from './components/SampleMap';
import TwitterAuth from './components/TwitterAuth';
import { Grid, Row, Col } from 'react-flexbox-grid';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <TwitterAuth />
      </header>
      <body>
        <Grid fluid>
          <Row>
            <Col xs={6} md={3}>
              <FormSearch />
            </Col>
            <Col xs={6} md={9}>
              <SampleMap />
            </Col>
          </Row>
        </Grid>
      </body>
    </div>
  );
}

export default App;
