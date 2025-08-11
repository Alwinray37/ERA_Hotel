// this is where the guests can view their reservations

export default function ViewReservations() {

    const dummyData = [
        {
            "id": 1,
            "email": "john@email.com",
            "reservationNumber": "res10001"
        },
        {
                "id": 2,
                "email": "tomK@email.com",
                "reservationNumber": "res10002"
        },

           {
                    "id": 3,
                    "email": "tomK@email.com",
                    "reservationNumber": "res10002"
           }

    ];
    function AllReservations (){

        }

    return (
        <div className="main-content">
            <form>
                <h3>View Reservations</h3>
                <input placeholder="Enter Email" />
            </form>

    <button className='btn btn-primary mb-2' onClick={AllReservations}>Enter</button>
        </div>

    )

}
