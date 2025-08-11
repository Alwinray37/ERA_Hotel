import axios from 'axios';

const urlbase = 'http://localhost:8080/api/guests';

export const listGuests = async () => {
  try {
    const response = await axios.get(urlbase);
    return response.data;
  } catch (error) {
    console.error('Error fetching guest data:', error);
    throw error;
  }
}

// This function is used to create a new guest
// It should be called when a new guest books a room
export const createGuest = async (guestData) => {
    // guest data should just be name, email and phone
    const { name, email, phone } = guestData;
    if (!name || !email || !phone) {
        throw new Error('Invalid guest data');
    }

  try {
    const response = await axios.post(urlbase, guestData);
    return response.data;
  } catch (error) {
    console.error('Error creating guest:', error);
    throw error;
  }
  
}

// get guest by email
export const getGuestByEmail = async (email) => {
  try {
    const response = await axios.get(`${urlbase}/email/${email}`);
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 404) {
      return null; // Guest not found
    }
    console.error('Error fetching guest by email:', error);
    throw error;
  }
}   