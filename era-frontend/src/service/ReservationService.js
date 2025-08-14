// functions to get and create reservation objects
import axios from 'axios';
const urlbase = 'http://localhost:8080/api/reservations';
const urlAppendGuest = 'http://localhost:8080/api/guests';
const urlAppendRoom = 'http://localhost:8080/api/rooms';

export const listReservations = async () => {
  try {
    const response = await axios.get(urlbase);
    return response.data;
  } catch (error) {
    console.error('Error fetching reservation data:', error);
    throw error;
  }
}

export const createReservation = async (reservationData) => {
  try {
    const response = await axios.post(urlbase, reservationData);
    return response.data;
  } catch (error) {
    console.error('Error creating reservation:', error);
    throw error;
  }
}

export const getReservationById = async (id) => {
  try {
    const response = await axios.get(`${urlbase}/${id}`);
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      return null; // Reservation not found
    }
    console.error('Error fetching reservation by ID:', error);
    throw error;
  }
}

export const updateReservation = async (id, reservationData) => {
  try {
    const response = await axios.put(`${urlbase}/${id}`, reservationData);
    return response.data;
  } catch (error) {
    console.error('Error updating reservation:', error);
    throw error;
  }
}

// function to append reservation Id to guest reservation list 
export async function appendResIdToGuest(guestId, reservationId) { 
    try {
        const response = await axios.post(`${urlAppendGuest}/${guestId}/reservations`, reservationId, { headers: { 'Content-Type': 'application/json' }} );
        return response.data;   
    } catch (error){
        console.log("error appending ResId to guest Res.");
        throw error;
    }
}

// function to append reservation Id to room reservations list 
export const appendResIdToRoom = async (roomId, resId) => {
    try {
        const res = await axios.post(`${urlAppendRoom}/${roomId}/reservations`, resId, {headers: { 'Content-Type': 'application/json' }});
        return res.data;
    } catch (error) {
        console.log("Error appending resID to room");
        throw error;
    }
}
