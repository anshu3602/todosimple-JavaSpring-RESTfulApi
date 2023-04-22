

<script lang="ts">
  import { onMount } from 'svelte';
  import Table from "../components/Table.svelte"
  import Counter from './Counter.svelte';
  import welcome from '$lib/images/svelte-welcome.webp';
  import welcome_fallback from '$lib/images/svelte-welcome.png';
  import Button from '@smui/button';
  import Dialog from '../components/Dialog.svelte';

  type Post = {
    createdAt: Date;
    image: any;
    content: string;
    title: string;
    id: number;
   };

    let items: Post[] = [];
    let loaded = false;

    onMount(() => loadThings(false))

    function loadThings(wait: boolean) {
            if (typeof fetch !== 'undefined') {
                loaded = false;

                fetch('http://localhost:5000')
                    .then((response) => response.json())
                    .then((json) =>
                        setTimeout(
                            () => {
                                items = json;
                                loaded = true;
                            },
                            // Simulate a long load time.
                            wait ? 2000 : 0
                        )
                    );
            }
    }
</script>
let open = false;

<div style="display:flex; justify-content:space-between">
    <Button on:click={() => (open = true)}>Add New</Button>
</div>
<Table {items} {loaded} />

<Dialog {open} />

<Table items={items} loaded={loaded}/>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section>
	<h1>
		<span class="welcome">
			<picture>
				<source srcset={welcome} type="image/webp" />
				<img src={welcome_fallback} alt="Welcome" />
			</picture>
		</span>

		to your new<br />SvelteKit app
	</h1>

	<h2>
		try editing <strong>src/routes/+page.svelte</strong>
	</h2>

	<Counter />
</section>

<style>
	section {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		flex: 0.6;
	}

	h1 {
		width: 100%;
	}

	.welcome {
		display: block;
		position: relative;
		width: 100%;
		height: 0;
		padding: 0 0 calc(100% * 495 / 2048) 0;
	}

	.welcome img {
		position: absolute;
		width: 100%;
		height: 100%;
		top: 0;
		display: block;
	}
</style>
