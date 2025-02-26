console.log("Contacts.js loaded successfully!");
const viewContactModal = document.getElementById('viewContactModal')
const baseUrl = "http://localhost:8080"
// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
    id: 'viewContactModal',
    override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactData(id){
    // TODO: Call function to fetch data
    try{
        const data = await(await fetch(`${baseUrl}/api/contacts/${id}`)).json();
        console.log("Data: ", data);

        document.querySelector('#contactName').innerHTML=data.name;
        document.querySelector('#contactEmail').innerHTML=data.email;
        document.querySelector('#contactImage').src = data.picture;
        document.querySelector('#contactAddress').innerHTML=data.address;
        document.querySelector('#contactPhone').innerHTML=data.phoneNumber;
        document.querySelector('#contactDescription').innerHTML=data.description;
        const contactFavorite = document.querySelector('#contactFavorite');
        if(data.favorite) {
            contactFavorite.innerHTML = "<i class='fa-solid fa-star w-6 h-6 text-yellow-400'></i><i class='fa-solid fa-star w-6 h-6 text-yellow-400'></i><i class='fa-solid fa-star w-6 h-6 text-yellow-400'></i><i class='fa-solid fa-star w-6 h-6 text-yellow-400'></i><i class='fa-solid fa-star w-6 h-6 text-yellow-400'></i>";
        } else {
            contactFavorite.innerHTML = "<i class='fa-regular fa-star w-6 h-6 text-yellow-400'></i><i class='fa-regular fa-star w-6 h-6 text-yellow-400'></i><i class='fa-regular fa-star w-6 h-6 text-yellow-400'></i><i class='fa-regular fa-star w-6 h-6 text-yellow-400'></i><i class='fa-regular fa-star w-6 h-6 text-yellow-400'></i>";
        }
        document.querySelector('#contactWebsite').href=data.websiteLink;
        document.querySelector('#contactWebsite').innerHTML=data.websiteLink;
        document.querySelector('#contactLinkedIn').href=data.linkedInLink;
        document.querySelector('#contactLinkedIn').innerHTML=data.linkedInLink;
        openContactModal();
    } catch (error) {
        console.error("Error: ", error);
    }
}

function deleteContact(id){
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Delete"
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            const url=`${baseUrl}/user/contacts/delete/${id}`;
            window.location.replace(url);

        }
    });
}