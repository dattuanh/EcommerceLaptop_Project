const form = document.getElementById("todo-form");
const inputName = document.getElementById("todo-input");
const inputLink = document.getElementById("todo-link");
const todoLane = document.getElementById("todo-lane");

form.addEventListener("submit", (e) => {
    e.preventDefault();
    var child = form.querySelectorAll("input");
    

    const name = child[0].value;
    const link = child[1].value;
    
    if (!name)
        return;

    $.ajax({
        url: "CreateNavLevel1",
        data: {navName: name, link: link},
        type: "GET",
        success: function (data) {
            todoLane.hidden=false;
            const newTask = document.createElement("p");
            newTask.classList.add("task");
            newTask.setAttribute("draggable", "true");
            newTask.innerHTML = name + data;

            newTask.addEventListener("dragstart", () => {
                newTask.classList.add("is-dragging");
            });

            newTask.addEventListener("dragend", () => {
                newTask.classList.remove("is-dragging");
            });

            todoLane.appendChild(newTask);

            inputName.value = "";
            inputLink.value = "http://localhost:9999/EcommerceLaptopSale/";
        }
    });
});