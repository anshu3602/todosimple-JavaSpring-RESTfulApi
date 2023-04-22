/** @type {import('./$types').PageLoad} */
export async function load({ params }) {
    const { id } = params
    const data = await fetch('http://localhost:5000/${id}').then(res => res.json());
    return data
}