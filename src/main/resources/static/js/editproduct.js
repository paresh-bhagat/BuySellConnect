function addNewDiv() {
    const dynamicDivContainer = document.getElementById('dynamicDivContainer');
    
    // Create a new div element
    const newDiv = document.createElement('div');
    newDiv.className = 'container border rounded-5';
    newDiv.innerHTML = `<div class="row pt-4">
                            <div class="col-2"><p class="fw-light">Title</p></div>
                            <div class="mb-3 pb-2 text-center col-10">
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="1"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-2"><p class="fw-light">Content</p></div>
                            <div class="mb-3 pb-2 text-center col-10">
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="1"></textarea>
                            </div>
                        </div>
                        <button type="button" class="btn btn-outline-danger m-2 btn-sm" onclick="removeDiv(this)">Delete</button>`;
    
    // Append the new div to the dynamicDivContainer
    dynamicDivContainer.appendChild(newDiv);
}

function removeDiv(button) {
    // Get the parent div (the div containing the close button)
    const parentDiv = button.parentElement;
    
    // Remove the parent div from the dynamicDivContainer
    parentDiv.remove();
}