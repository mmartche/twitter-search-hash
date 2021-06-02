import React from 'react';
import logo from './logo.svg';
import './App.css';
import SampleMap from './components/sampleMap';
import FormSearch from './components/FormSearch';
import TwitterAuth from './components/TwitterAuth';

const location = {
  address: '1600 Amphitheatre Parkway, Mountain View, california.',
  lat: 37.42216,
  lng: -122.08427,
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <TwitterAuth />
      </header>
      <body>
        <FormSearch />
        <img src={logo} className="App-logo" alt="logo" />
      </body>
    </div>
  );
}

export default App;
