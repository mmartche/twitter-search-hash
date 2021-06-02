import React from 'react'
import GoogleMapReact from 'google-map-react'

const LocationPin = ({ text } : {text:any}) => (
  <div className="pin">
    <div className="pin-icon" >a</div>
    <p className="pin-text">{text}</p>
  </div>
)

const SampleMap = ({ locationMap, zoomLevel } : { locationMap:any, zoomLevel:any}) => (
  <div className="map">
    <h2 className="map-h2">Come Visit Us At Our Campus</h2>

    <div className="google-map" style={{ height: '100vh', width: '100%' }}>
      <GoogleMapReact
        bootstrapURLKeys={{ key: 'AIzaSyAi34_XNXdjOEXgasUDmuF--uSuIB2XxqI' }}
        defaultCenter={locationMap}
        defaultZoom={zoomLevel}
      >
      </GoogleMapReact>
    </div>
  </div>
)

export default SampleMap
