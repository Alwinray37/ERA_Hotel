import axios from 'axios';

const urlbase = 'http://localhost:8080/api/rooms';

export const listRooms = async () => {
  try {
    const response = await axios.get(urlbase);
    return response.data;
  } catch (error) {
    console.error('Error fetching room data:', error);
    throw error;
  }
};
