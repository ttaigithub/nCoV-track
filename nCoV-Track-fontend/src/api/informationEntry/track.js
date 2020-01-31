import request from '../ajax/server'

export function queryTrackList(params) {
  return request.post('/track/query', params)
}
export function queryTrackDetail(params) {
  return request.post('/track/queryDetail', params)
}
export function addTrack(params) {
  return request.post('/track/add', params)
}

export function updateTrack(params) {
  return request.post('/track/update', params)
}

export function deleteTrack(params) {
  return request.post('/track/delete', params)
}
