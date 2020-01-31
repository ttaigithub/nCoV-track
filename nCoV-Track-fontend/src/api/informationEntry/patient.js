import request from '../ajax/server'

export function queryPatientList(params) {
  return request.post('/patient/query', params)
}

export function addPatient(params) {
  return request.post('/patient/add', params)
}

export function updatePatient(params) {
  return request.post('/patient/update', params)
}

export function deletePatient(params) {
  return request.post('/patient/delete', params)
}
